package com.example.demo.Repositories;

import com.example.demo.Domain.Term;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TermRepository extends CrudRepository<Term,Long> {


}
