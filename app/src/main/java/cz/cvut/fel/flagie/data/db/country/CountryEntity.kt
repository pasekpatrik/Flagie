package cz.cvut.fel.flagie.data.db.country

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.cvut.fel.flagie.data.model.CountryFlags
import cz.cvut.fel.flagie.data.model.CountryName

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: CountryName,
    val capital: List<String>? = emptyList(),
    val flags: CountryFlags,
    val population: Int? = null,
    val region: String? = null
)