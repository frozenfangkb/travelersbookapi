package com.davidrodriguez.travelersbookapi;

import com.mongodb.lang.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TravelersbookapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelersbookapiApplication.class, args);
	}

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		// we use exact UTC date pointing to 00:00 of given day to store LocalDate
		return new MongoCustomConversions(List.of(

				new Converter<LocalDate, Date>() {
					@Override
					public Date convert(@NonNull LocalDate source) {
						return new Date(source.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
					}
				},

				new Converter<Date, LocalDate>() {
					@Override
					public LocalDate convert(@NonNull Date source) {
						return source.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
					}
				}

		));
	}

}
