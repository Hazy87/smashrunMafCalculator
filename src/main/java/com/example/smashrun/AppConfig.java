package com.example.smashrun;

import com.example.smashrun.Maf.MafCalculator;
import com.example.smashrun.Maf.MafRangeProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig{

    @Bean public String accessToken(){
        return access_token;
    }
    @Value("${smashrun.access_token}")
    public String access_token;

    @Bean
    public TimeFormatter timeFormatter(){ return new TimeFormatter();}
    @Bean
    public MyHttpClient httpClient(){
        return new MyHttpClient();
    }

    @Bean
    public PbCalculator pbCalculator(){ return new PbCalculator();}
    @Bean
    public SmashRunApiClient smashRunClient(){
        return new SmashRunApiClient(httpClient());
    }

    public MafRangeProvider mafRangeProvider(){
        return new MafRangeProvider();
    }
    @Bean
    public MafCalculator mafCalculator(){ return new MafCalculator(mafRangeProvider());}
}

