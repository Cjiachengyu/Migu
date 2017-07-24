package cn.eclassmate.qzy.persistence;

import cn.eclassmate.qzy.domain.Que;
import cn.eclassmate.qzy.domain.QueChoice;
import cn.eclassmate.qzy.domain.QueZsd;
import cn.eclassmate.qzy.domain.Zsk;
import cn.eclassmate.qzy.domain.Zsl;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueMapper
{
    List<Zsk> getAllZsk();

    List<Zsk> getAllZskWithZsd();

    List<Zsl> getAllZslWithZsd();

    int getRandomQueToCreateBagCount(
            @Param("userId") int userId,
            @Param("zskId") int zskId,
            @Param("zsd1Id") int zsd1Id,
            @Param("zsd2Id") int zsd2Id
            );

    Que getRandomQueToCreateBag(
            @Param("userId") int userId,
            @Param("zskId") int zskId,
            @Param("zsd1Id") int zsd1Id,
            @Param("zsd2Id") int zsd2Id,
            @Param("selectIndex") int selectIndex
            );

    List<Que> getQueListForEditor(
            @Param("creatorId") int creatorId,
            @Param("queStatus") int queStatus,
            @Param("zskId") int zskId,
            @Param("zsd1Id") int zsd1Id,
            @Param("zsd2Id") int zsd2Id,
            @Param("limitBegin") int limitBegin,
            @Param("limitCount") int limitCount);

    int getQueListForEditorCount(
            @Param("creatorId") int creatorId,
            @Param("queStatus") int queStatus,
            @Param("zskId") int zskId,
            @Param("zsd1Id") int zsd1Id,
            @Param("zsd2Id") int zsd2Id);

    Que getQue(int queId);

    int deleteQue(int queId);

    int insertQue(Que que);

    int updateQue(Que que);

    void insertQueChoiceList(List<QueChoice> queChoiceList);

    void deleteQueChoice(QueChoice queChoice);

    void insertQueZsd(List<QueZsd> list);

    void deleteQueZsd(QueZsd queZsd);

    List<QueZsd> getQueZsd(int queId);

    int updateQueFeedBackStatus(int queId);
}
