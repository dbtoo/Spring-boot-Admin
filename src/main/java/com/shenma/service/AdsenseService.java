package com.shenma.service;

import com.shenma.model.Adsense;
import com.shenma.model.News;
import com.shenma.util.Constant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface AdsenseService {
	
	@Select("SELECT * FROM `ADSENSE` WHERE ID = #{id};")
	Adsense findById(Adsense adsense);
	
	@Select({
		"<script>",
		"SELECT * FROM `ADSENSE` ",
			"WHERE state = 1",
			"<when test='type!=0'>",
			"AND type LIKE CONCAT(#{type})",
			"</when>",
			"<when test='place!=0'>",
			"AND place LIKE CONCAT(#{place})",
			"</when>",
			"order by addDate desc limit #{start},#{end}",
			"</script>"
	})
	List<Adsense> list(Adsense adsense);
	
	@Select({
			"<script>",
			"SELECT count(*) FROM `ADSENSE`",
			"WHERE state = 1",
			"<when test='type!=null'>",
			"AND type LIKE CONCAT(#{type})",
			"AND place LIKE CONCAT(#{place})",
			"</when>",
			"</script>"
	})
	int count(Adsense adsense);
	
	@Insert("INSERT INTO `adsense` (`id`,`type`,`place`,`addDate`,`updateDate`,`state`) VALUES (null,#{type},#{place},now(),now(),1);")
	int insert(Adsense adsense);

	@Update("UPDATE `adsense` SET `type` = #{type}, `place` = #{place}, `updateDate` = now()  WHERE `id` = #{id};")
	int update(Adsense adsense);
	
	@Update("UPDATE `adsense` SET `state` = #{state} WHERE `id` = #{id};")
	int updateState(Adsense adsense);
	
}
