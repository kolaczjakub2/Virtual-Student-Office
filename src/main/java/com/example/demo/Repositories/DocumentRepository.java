package com.example.demo.Repositories;

import com.example.demo.Domain.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document,Long> {
}
