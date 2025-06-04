package users.userManagement.Users;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UsersConfig {

    PasswordEncoder passwordEncoder;
    public UsersConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository) {
        return args -> {
             Users  blessing = new Users(
                     "Tchatchoua Blessing",
                     "blessing.tchatchoua237@gmail.com",
                     passwordEncoder.encode(" BlessingNaBae2025!")
             );

            Users  elvis = new Users(
                    " Ngong Elvis",
                    "elvisfireboy@yahoo.com",
                    passwordEncoder.encode("NaMeBeElvis!23")
            );

            Users  carine = new Users(
                    "Mbua Carine",
                    "carinemami237@outlook.com",
                    passwordEncoder.encode("MamiCarine$Strong1")
            );
            Users junior = new Users(
                    "Neba Junior",
                    "junior4bamenda@gmail.com",
                    passwordEncoder.encode(" BdaBoy_Junior99")
            );

            Users  tracy  = new Users(
                    "Ekambi Tracy",
                    "tracytori237@gmail.com",
                    passwordEncoder.encode(" TracyNoDeyPlay@24")
            );

            Users  didier = new Users(
                    " Fonkeu Didier",
                    "fonkeurider@ymail.com",
                    passwordEncoder.encode("FonBoy_#Cameroon2025")
            );

            Users chantal  = new Users(
                    "Abena Chantal",
                    "chantal_lovely@icloud.com",
                    passwordEncoder.encode("Abenababe237!!")
            );

            usersRepository.saveAll(List.of(blessing, elvis, carine, junior,
                    tracy, didier, chantal));
        };
    }
}
