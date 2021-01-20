import React, { useEffect, useState } from 'react';
import { Alert, ScrollView, StyleSheet, Text } from 'react-native';
import { TouchableWithoutFeedback } from 'react-native-gesture-handler';
import { fetchOrders } from '../api';
import Header from '../Header';
import OrderCard from '../OrderCard';
import { Order } from '../Types';

function Orders() {

    const INTERNAL_SERVER_ERROR = "internal server error";
    const [ orders, setOrders ] =  useState<Order[]>([]);
    const [ isLoading, setIsLoading ] = useState(false)

    useEffect(() => {
        setIsLoading(true);
        fetchOrders()
            .then(response => setOrders(response.data))
            .catch(() => Alert.alert(INTERNAL_SERVER_ERROR))
            .finally(() => setIsLoading(false));
    }, []);

    const handleOnPress = ()=>{

    }
    return (
        <>
            <Header />
            <ScrollView style={styles.container}>
                { isLoading ? 
                    (<Text> Buscando Pedidos ...</Text>) : (
                    orders.map(order =>(
                        <TouchableWithoutFeedback key={order.id}>
                            <OrderCard order={order}/>
                        </TouchableWithoutFeedback>
                    ))
                )}
            </ScrollView>
        </>
    );
}

const styles = StyleSheet.create({ container:{
    paddingRight: '5%',
    paddingLeft: '5%',
 }
});

export default Orders;
