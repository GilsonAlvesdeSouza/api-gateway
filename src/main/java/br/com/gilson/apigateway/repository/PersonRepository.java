package br.com.gilson.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gilson.apigateway.model.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

}
