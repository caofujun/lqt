package com.nis.mkzb.dao;

import com.nis.mkzb.entity.MkZbRecord;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MkZbRecordDao {
	List<MkZbRecord> findZbRecordList(MkZbRecord arg0);

	List<HashMap<String, Object>> caseRegist(MkZbRecord arg0);

	void updateSt003ZBFlag(@Param("zyid") String arg0, @Param("tranFlag") String arg1, @Param("tranDate") Date arg2);

	List<HashMap<String, Object>> caseRegistY(MkZbRecord arg0);

	List<HashMap<String, Object>> findBrjbxx(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findjcjb(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findSsqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findYgys(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findQhxcz(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findGrbwxxqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findGrbwxgqhxcz(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findJcbbxxqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findBbjcbytxxqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findYmsyjg(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findKjywsyqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findIcubrjbqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findIcujbzd(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findZxjmcgqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findHxjsyqk(@Param("zyid") String arg0);

	List<HashMap<String, Object>> findDngsyqk(@Param("zyid") String arg0);
}