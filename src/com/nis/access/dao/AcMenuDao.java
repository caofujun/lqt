package com.nis.access.dao;

import com.nis.access.entity.AcMenu;
import com.nis.comm.entity.TreeEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcMenuDao {
	void save(AcMenu arg0);

	void delete(@Param("menuId") String arg0, @Param("menuNo") String arg1);

	void update(AcMenu arg0);

	AcMenu get(@Param("menuId") String arg0);

	List<AcMenu> findAcMenu(AcMenu arg0);

	int findAcMenuCount(AcMenu arg0);

	List<AcMenu> getAll();

	AcMenu checkExtis(AcMenu arg0);

	List<AcMenu> findByOwnership(AcMenu arg0);

	List<AcMenu> getAllPatentMenu(@Param("ownership") String arg0);

	List<AcMenu> getMenuNo(@Param("menuNo") String arg0);

	List<AcMenu> getMenuByParentNo(@Param("parentMenuNo") String arg0, @Param("roleId") String arg1);

	void deleteByMenuNo(@Param("menuNo") String arg0);

	List<AcMenu> getAllMenu(AcMenu arg0);

	List<AcMenu> getReportMenu();

	List<AcMenu> getMenuNos(@Param("menuNos") String[] arg0, @Param("roleId") String arg1);

	List<AcMenu> findMenuNos(@Param("menuNos") String[] arg0);

	List<TreeEntity> getReportMenuRoot();

	List<TreeEntity> getReportMenuLeaf(@Param("parentMenuNo") String arg0, @Param("roleId") String arg1);

	List<AcMenu> getMenuRoot(AcMenu arg0);

	List<AcMenu> getMenuLeaf(AcMenu arg0);
}