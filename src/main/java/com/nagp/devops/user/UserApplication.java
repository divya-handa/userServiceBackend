package com.nagp.devops.user;

import com.nagp.devops.user.entities.User;
import com.nagp.devops.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean(name = "restTemp")
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	ApplicationRunner sampleData(){
		return args -> {
			if(userRepository.findAll().size()<5){
				userRepository.save(new User(30l, "a", "delhi", "122002", true));
				userRepository.save(new User(31l, "ab", "Mumbai", "122005", true));
				userRepository.save(new User(32l, "ac", "Hyderabad", "122502", false));
				userRepository.save(new User(33l, "ad", "Amritsar", "125102", true));
				userRepository.save(new User(34l, "ae", "Chandigarh", "322002", true));
			}

		};
	}
}
