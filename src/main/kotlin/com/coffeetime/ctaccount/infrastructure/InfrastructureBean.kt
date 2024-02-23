package com.coffeetime.ctaccount.infrastructure

import com.coffeetime.ctaccount.infrastructure.persistent.rdbms.property.RdbmsProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Paths
import javax.sql.DataSource

@Configuration
class InfrastructureBean {

    // TODO : Use Etcd
    @Configuration
    class RdbmsDBRepository {
        fun getDatabaseProperties(rdsConnectionInfoPath: String): RdbmsProperty {
            val lines = Files.readAllLines(Paths.get(rdsConnectionInfoPath))
            if (lines.size < 4) throw IllegalArgumentException("Configuration file is invalid.")

            val rdbmsProperty = RdbmsProperty(
                url = "jdbc:mariadb://${lines[0]}:${lines[1]}/user?useUnicode=true&characterEncoding=utf-8",
                username = lines[2],
                password = lines[3],
                driverClassName = "org.mariadb.jdbc.Driver"
            )
            return rdbmsProperty
        }
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        fun dataSource(): DataSource {
            // 로컬 파일 경로 지정
            val rdsConnectionInfoPath = "D:\\aws_rds_access_info.txt"

            // DB 연결 정보 로드
            val properties = getDatabaseProperties(rdsConnectionInfoPath)

            // HikariDataSource 구성
            return DataSourceBuilder.create()
                .url(properties.url)
                .username(properties.username)
                .password(properties.password)
                .build()
        }
    }
}