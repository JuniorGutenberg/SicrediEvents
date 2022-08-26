package com.orbital.core.data.base.model

import com.orbital.core.data.base.local.BaseDao

abstract class BaseServiceDao<ENTITY,DAO: BaseDao<ENTITY>>(var dao:DAO){
    fun insert(entity: ENTITY):Long{
        return dao.insert(entity)
    }

    fun insertAll(entityList:List<ENTITY>){
        dao.insertAll(entityList)
    }
    fun delete(entity: ENTITY){
        dao.delete(entity)
    }
    fun update(entity: ENTITY){
        dao.update(entity)
    }
}