package io.macaca.genie.repository;

import io.macaca.genie.entities.RootDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootDirectoryRepository extends JpaRepository<RootDirectory, Long> {


}
