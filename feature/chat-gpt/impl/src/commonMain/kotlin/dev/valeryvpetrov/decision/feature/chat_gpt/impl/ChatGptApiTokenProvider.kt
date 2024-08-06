package dev.valeryvpetrov.decision.feature.chat_gpt.impl

import dev.valeryvpetrov.decision.base.api.Provider

class ChatGptApiTokenProvider : Provider<String> {
    override fun get(): String = BuildConfig.CHAT_GPT_API_TOKEN
}