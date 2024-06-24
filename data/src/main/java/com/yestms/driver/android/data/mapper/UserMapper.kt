package com.yestms.driver.android.data.mapper

import com.yestms.driver.android.data.remote.response.stats.DriverStatsResponse
import com.yestms.driver.android.domain.model.user.DriverStatsModel

object UserMapper {

    val userMapper:
            Mapper<DriverStatsResponse?, DriverStatsModel> =
        { remote ->
            DriverStatsModel(
                allMiles = remote?.allMiles.or0(),
                earn = remote?.earn.or0()
            )
        }
}