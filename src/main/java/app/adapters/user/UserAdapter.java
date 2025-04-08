package app.adapters.user;

import app.adapters.person.entity.PersonEntity;
import app.adapters.user.repository.UserRepository;
import app.adapters.user.entity.UserEntity;
import app.adapters.user.repository.UserRepository;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.UserPort;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAdapter implements UserPort {
    private UserRepository userRepository;
    @Override
    public User findByUserId(long userId) {
        return null;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        UserEntity userEntity =userEntityAdapter(user);
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

    private PersonEntity personEntityAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setDocument(person.getDocument());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        personEntity.setRole(person.getRole());
        return personEntity;
    }

    private UserEntity userEntityAdapter(User user) {
        PersonEntity personEntity = personEntityAdapter(user);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

}
