package com.example.ruleengine.repository;

import com.example.ruleengine.model.RuleDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDependencyRepository extends JpaRepository<RuleDependency, Long> {
}
