package github.paulmburu.repository.repository

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.WasteType
import github.paulmburu.domain.repository.WasteManagementRepository
import github.paulmburu.local.dao.ProgressDao
import github.paulmburu.local.dao.WasteManagementDao
import github.paulmburu.local.mappers.toDomain
import github.paulmburu.local.mappers.toLocal
import github.paulmburu.local.mappers.toProgressEntity
import github.paulmburu.network.api.WasteManagementApi
import github.paulmburu.network.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class WasteManagementRepositoryImpl @Inject constructor(
    private val wasteManagementApi: WasteManagementApi,
    private val wasteManagementDao: WasteManagementDao,
    private val progressDao: ProgressDao
) : WasteManagementRepository {

    override fun fetchWasteTypes(): Flow<Resource<List<WasteType>>> = flow {
        try {
            val result = wasteManagementApi.fetchWasteTypes()
            when {
                result.isSuccessful -> {
                    wasteManagementDao.insertWasteTypes(result.body()!!.data.map {
                        it.toDomain().toLocal()
                    })
                    emit(
                        Resource.Success(result.body()!!.data.map { it.toDomain() })
                    )
                }
                else -> emit(Resource.Error(message = result.message()))
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun insertWasteTypes(wasteType: List<WasteType>) {
        wasteManagementDao.insertWasteTypes(wasteType.map { it ->
            it.toLocal()
        })
    }

    override fun getWasteTypes(): Flow<Resource<List<WasteType>>> = flow {
        try {
            wasteManagementDao.getWasteTypes().collect {
                val data = it.map { wasteTypeEntity -> wasteTypeEntity.toDomain() }
                emit(
                    Resource.Success(
                        data
                    )
                )
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override suspend fun insertProgressData(wasteType: WasteType) {
        progressDao.insertProgress(wasteType.toProgressEntity())
    }

    override fun getProgressData(): Flow<Resource<List<WasteType>>> = flow {
        try {
            progressDao.getProgress().collect {
                val data = it.map { wasteTypeEntity -> wasteTypeEntity.toDomain() }
                emit(
                    Resource.Success(
                        data
                    )
                )
            }
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
            Timber.e(e)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }


}