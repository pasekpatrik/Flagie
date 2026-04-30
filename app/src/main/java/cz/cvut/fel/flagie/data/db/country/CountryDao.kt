package cz.cvut.fel.flagie.data.db.country

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM country")
    fun getAllCountries(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM country WHERE id = :countryId LIMIT 1")
    suspend fun getCountryById(countryId: Long): CountryEntity?

    @Delete
    suspend fun deleteCountry(country: CountryEntity)

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}