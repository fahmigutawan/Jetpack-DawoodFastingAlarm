package com.frgutawan.dawoodfastingalarm.utils

import com.frgutawan.dawoodfastingalarm.model.DawoodFastingScreenLang
import com.frgutawan.dawoodfastingalarm.model.Languages
import com.frgutawan.dawoodfastingalarm.model.NormalAlarmScreenLang
import com.frgutawan.dawoodfastingalarm.model.SettingScreenLang

object LangMapper {
    fun englishLang() =
        Languages(
            DawoodFastingScreenLang(
                hadith = "The most beloved fasting to Allah was the fasting of Prophet Dawood, " +
                        "who used to fast on alternate days. " +
                        "And the most beloved prayer to Allah was the prayer of Prophet Dawood, " +
                        "who used to sleep for half of the night and pray for 1/3 of it " +
                        "and (again) sleep for a sixth of it.",
                hadithNarator = "Narrated by Bukhari",
                activateDawoodAlarm = "Activate Dawood Fasting Alarm",
                startFromNextAlarm = "Start from Next Alarm",
                aboutStartFromNextAlarm = "Fasting of Prophet Dawood is fast on alternate days. " +
                        "This mean that this alarm should be continuously active every two days. " +
                        "\n\nBy clicking \"Start from Next Alarm\", mean that you command the system " +
                        "to start your alarm schedule by next alarm time dan it will continuously turn on the alarm every two days " +
                        "by its start day.",
                reminderDayBefore = "Reminder a Day Before"
            ),
            NormalAlarmScreenLang(
                normalAlarmTitle = "Normal Alarm"
            ),
            SettingScreenLang(
                test = "TEST"
            )
        )

    fun indonesiaLang() =
        Languages(
            DawoodFastingScreenLang(
                hadith = "Puasa yang lebih disukai oleh Allah ialah puasa Nabi Daud, " +
                        "Ia berpuasa satu hari lalu berbuka satu hari. " +
                        "Dan shalat yang paling disukai oleh Allah adalah shalat Nabi Daud. " +
                        "Ia tidur seperdua malam, bangun sepertiganya, lalu tidur seperenamnya.",
                hadithNarator = "HR. Bukhari",
                activateDawoodAlarm = "Aktifkan Alarm Puasa Daud",
                startFromNextAlarm = "Mulai dari Alarm Berikutnya",
                aboutStartFromNextAlarm = "Puasa Nabi Daud adalah puasa satu hari, berbuka satu hari. " +
                        "Artinya bahwa alarm harus aktif secara berlanjut setiap dua hari sekali. " +
                        "\n\n Dengan menekan \"Mulai dari Alarm Berikutnya\", berarti bahwa " +
                        "anda memerintahkan sistem untuk menjadwalkan alarm setiap dua hari sekali " +
                        "dimulai dari waktu alarm berikutnya setelah tombol ditekan",
                reminderDayBefore = "Pengingat Satu Hari Sebelumnya"
            ),
            NormalAlarmScreenLang(
                normalAlarmTitle = "Alarm Biasa"
            ),
            SettingScreenLang(
                test = "TEST"
            )
        )
}