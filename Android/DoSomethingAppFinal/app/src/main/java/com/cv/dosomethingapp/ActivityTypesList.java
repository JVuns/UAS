package com.cv.dosomethingapp;

import java.util.ArrayList;

public class ActivityTypesList {
    private static String[] typeNames = {
            "BUSYWORK",
            "CHARITY",
            "DIY",
            "EDUCATIONAL",
            "COOKING",
            "SOCIAL",
            "RELAXATION",
            "MUSIC",
            "RECREATIONAL"
    };

    private static String[] typeDetails = {
            "Activities with this type may need some bits of effort to be finished. Definitely challenging, but it can be good to spend your time well though.",
            "Doing activities with this type will help you to take more care about the others, by donating and giving them what they deserve to have but they currently don't have.",
            "DIY stands for Do-It-Yourself, activities with this type may help to improve your creativity and productivity!",
            "Education things usually sound boring but it actually is not! Activities with this type may help you to learn new things and improve your knowledge.",
            "To get out from the boredom, why not to try cooking then? Experimental and fun thing to do. A bonus value will be added if the result tastes good as well!",
            "In this world we will always need presence of the others. Activities with this type may help you to socialize with others and make new friends.",
            "Humans tend to relax a lot instead of working all the time. Activities with this type may help you to get rid of the stress and also to refresh your mind.",
            "Music is the best thing you can listen to when you are bored. Try to discover even more kinds of music by doing activities with this type.",
            "Recreation may be a great thing to do when you feel really stuck for some moments, especially when you have lots of work coming for you later on and you don't really know what to do."
    };

    static ArrayList<ActivityTypes> getListData() {
        ArrayList<ActivityTypes> list = new ArrayList<>();
        for (int i = 0; i < typeNames.length; i++) {
            ActivityTypes activityTypes = new ActivityTypes();
            activityTypes.setActivityType(typeNames[i]);
            activityTypes.setActivityTypeDetail(typeDetails[i]);
            list.add(activityTypes);
        }
        return list;
    }

}
