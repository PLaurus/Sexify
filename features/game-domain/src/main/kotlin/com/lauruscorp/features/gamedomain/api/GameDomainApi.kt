package com.lauruscorp.features.gamedomain.api

import com.lauruscorp.features.gamedomain.store.GameStore

interface GameDomainApi {
	fun getStore(): GameStore
}