//package com.fitapp.service;
//
//
//import com.fitapp.model.AppUser;
//import com.fitapp.model.Measurement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class ScheduledTasks {
//
//    @Autowired
//    private UserService userService;
//
//    @Async
//    @Scheduled(fixedRate = 5000)
//    public void ChangeUsersCalories() {
//
//        for (AppUser user:userService.findAllUsers()) {
//
//            if(user.getMeasurements().size() >= 2) {
//
//                List<Measurement> latestMeasuremntsList = user.getMeasurements().stream().skip(user.getMeasurements().size() - 2).collect(Collectors.toList());
//
//                Measurement oldMeasurements = latestMeasuremntsList.get(0);
//
//                Measurement newMeasurements = latestMeasuremntsList.get(latestMeasuremntsList.size()-1);
//
//                float kilogramDiff = newMeasurements.getWeight() / oldMeasurements.getWeight() - 1;
//
//
//                if(user.getGoal().equals("bulk")) {
//
//                    if(kilogramDiff < 0) {
//
//
//                        user.setCalories(user.getCalories() + (user.getCalories() * 10) / 100);
//                        userService.saveUser(user);
//
//                    } else if(kilogramDiff > 0.01) {
//
//                        System.out.println("Before" + user.getCalories());
//                        user.setCalories(user.getCalories() - (user.getCalories()*10)/100);
//                        System.out.println("After" + user.getCalories());
//                        userService.saveUser(user);
//                    }
//
//                } else {
//
//                    if(kilogramDiff < -0.01) {
//
//                        user.setCalories(user.getCalories() + (user.getCalories()*10)/100);
//                        userService.saveUser(user);
//
//                    } else if(kilogramDiff > 0) {
//
//                        user.setCalories(user.getCalories() - (user.getCalories()*10)/100);
//                        userService.saveUser(user);
//                    }
//
//                }
//
//            }
//        }
//    }
//}
