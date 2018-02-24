//package com.fitapp.service;
//
//
//import com.fitapp.model.AppUser;
//import com.fitapp.model.Meal;
//import com.fitapp.model.Measurement;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.stream.Collectors;
//
//@Component
//public class ScheduledTasks {
//
//
//    private final String TOPIC = "JavaSampleApproach";
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private MealService mealService;
//
//    @Autowired
//    private FoodService foodService;
//
//    @Autowired
//    private AndroidPushNotificationsService androidPushNotificationsService;
//
//
//    @Async
//    @Scheduled(cron = "15 * * * * *")
//    public void send() throws JSONException {
//
//        JSONObject body = new JSONObject();
//        body.put("to", "/topics/" + TOPIC);
//        body.put("priority", "high");
//
//        JSONObject notification = new JSONObject();
//        notification.put("title", "JSA Notification");
//        notification.put("body", "Happy Message!");
//
//        JSONObject data = new JSONObject();
//        data.put("Key-1", "JSA Data 1");
//        data.put("Key-2", "JSA Data 2");
//
//        body.put("notification", notification);
//        body.put("data", data);
//
///**
// {
// "notification": {
// "title": "JSA Notification",
// "body": "Happy Message!"
// },
// "data": {
// "Key-1": "JSA Data 1",
// "Key-2": "JSA Data 2"
// },
// "to": "/topics/JavaSampleApproach",
// "priority": "high"
// }
// */
//
//        HttpEntity<String> request = new HttpEntity<>(body.toString());
//
//        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
//        CompletableFuture.allOf(pushNotification).join();
//
//        try {
//            String firebaseResponse = pushNotification.get();
//
//            //return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//       // return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
//    }
//
//
//    @Async
//    @Scheduled(cron = "0 0 10 * * */THU")
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
//                        user.setCalories(user.getCalories() + (user.getCalories() * 10) / 100);
//
//                    } else if(kilogramDiff > 0.01) {
//
//                        System.out.println("Before" + user.getCalories());
//                        user.setCalories(user.getCalories() - (user.getCalories()*10)/100);
//                        System.out.println("After" + user.getCalories());
//
//                    }
//
//                } else {
//
//                    if(kilogramDiff < -0.01) {
//
//                        user.setCalories(user.getCalories() + (user.getCalories()*10)/100);
//
//                    } else if(kilogramDiff > 0) {
//
//                        user.setCalories(user.getCalories() - (user.getCalories()*10)/100);
//
//                    }
//
//                }
//
//            }
//
//            for (Meal meal: user.getDiet().getMeals()) {
//
//                mealService.calculateCaloriesPerMeal(user,meal);
//                foodService.calculateAmountofFood1(meal);
//            }
//
//            userService.saveUser(user);
//        }
//    }
//}
