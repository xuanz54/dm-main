package sut.edu.zyp.dormitory.manage.repository;

import sut.edu.zyp.dormitory.manage.entity.BuildingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "building", path = "building")
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, String> {

    /**
     * 楼宇信息
     *
     * @param name 楼宇名称
     * @return 楼宇信息
     */
    BuildingEntity findByName(String name);
}
