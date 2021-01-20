import React from 'react';
import { NavigationContainer, StackActions } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import Home from "./Home";
import Orders from "./Orders";

const stack = createStackNavigator();

function Routes(){
    return(
        <NavigationContainer>
            <stack.Navigator
                headerMode="none"
                screenOptions={{
                    cardStyle:{
                        backgroundColor: '#fff'
                    }
                }}
            >
                <stack.Screen name="Home" component={Home}></stack.Screen>
                <stack.Screen name="Orders"component={Orders}></stack.Screen>
            </stack.Navigator>
        </NavigationContainer>

    )
}
export default Routes