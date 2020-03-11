package com.koidev.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedOwner(
    @PrimaryKey val userId: Long,
    val reputation: Long,
    val userType: String,
    val profileImage: String,
    val displayName: String,
    val profileLink: String
)
