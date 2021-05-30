package com.company;

import lab5.test.TestByConsol;
import lab5.test.testApp;

public class Main {
    public static void main(String[] args) throws Exception {
        testApp app = new testApp();
        app.startApp();
        TestByConsol testConsole = new TestByConsol();
        testConsole.startAppConsole();
       // TestFile.main();
       // TestStoreObject storeObject = new TestStoreObject();
        // storeObject.main();
    }
}
