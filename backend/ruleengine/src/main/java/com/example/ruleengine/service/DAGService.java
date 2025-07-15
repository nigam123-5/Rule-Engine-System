package com.example.ruleengine.service;

import com.example.ruleengine.model.RuleDependency;
import com.example.ruleengine.repository.RuleDependencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DAGService {

    @Autowired
    private RuleDependencyRepository ruleDependencyRepository;

    public List<String> getExecutionOrder() throws Exception {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        List<RuleDependency> dependencies = ruleDependencyRepository.findAll();

        for (RuleDependency dep : dependencies) {
            graph.computeIfAbsent(dep.getDependsOn(), k -> new ArrayList<>()).add(dep.getRuleName());
            indegree.put(dep.getRuleName(), indegree.getOrDefault(dep.getRuleName(), 0) + 1);
            indegree.putIfAbsent(dep.getDependsOn(), 0);
        }

        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) queue.offer(entry.getKey());
        }

        List<String> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            sortedOrder.add(current);
            for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) queue.offer(neighbor);
            }
        }

        if (sortedOrder.size() != indegree.size()) {
            throw new Exception("Cycle detected in rule dependencies");
        }

        return sortedOrder;
    }
}
