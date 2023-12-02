package sut.edu.zyp.dormitory.manage.repository;

import sut.edu.zyp.dormitory.manage.entity.LiveEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "live", path = "live")
public interface LiveRepository extends PagingAndSortingRepository<LiveEntity, String> {
}
