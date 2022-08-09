package com.frgutawan.dawoodfastingalarm.model

data class Languages(
    val dawoodFastingScreen:DawoodFastingScreenLang,
    val normalAlarmScreen:NormalAlarmScreenLang,
    val setting:SettingScreenLang
)

data class DawoodFastingScreenLang(
    val hadith:String,
    val hadithNarator:String,
    val activateDawoodAlarm:String,
    val startFromNextAlarm:String,
    val aboutStartFromNextAlarm:String,
    val aboutStartFromNextAlarmExit:String,
    val reminderDayBefore:String
)

data class NormalAlarmScreenLang(
    val normalAlarmTitle:String,

)

data class SettingScreenLang(
    val test:String
)
