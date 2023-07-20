import React, { useState } from 'react';
import { validateEmail } from '../utils';
import { View, Text, StyleSheet, Image, TextInput, Pressable, Alert } from 'react-native';

const SubscribeScreen = () => {
  const [email, setEmail] = useState('');
  return (
    <View style={styles.container}>
      <View style={styles.headerWrapper}>
        <Image
          style={styles.logo}
          source={require("../assets/little-lemon-logo-grey.png")}
          resizeMode="cover"
          accessible={true}
          accessibilityLabel={"Little Lemon Logo"}
        />
      </View>
        <Text style={styles.regularText}>
            Subscribe to our newsletter for our latest delicious recipes!
        </Text>
        <TextInput
          style={styles.inputBox}
          value={email}
          onChangeText={setEmail}
          placeholder={'Type your email'}
          keyboardType='email-address'
          textContentType='emailAddress'
        />
        <Pressable style={ validateEmail(email) ? styles.button : [styles.button, {backgroundColor: 'silver'} ]} disabled={!validateEmail(email)} onPress={() => {
            Alert.alert("Thank you for subscribing, stay tuned!");
        }}>
          <Text style={styles.buttonText}>Subscribe</Text>
        </Pressable>
    </View>
  )
};

export default SubscribeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  headerWrapper: {
    flexDirection: "row",
    justifyContent: "center",
    marginTop: 30
  },
  regularText: {
    fontSize: 22,
    padding: 20,
    marginVertical: 20,
    color: 'dark-gray',
    textAlign: 'center',
  },
  logo: {
    width: 105,
    height: 125
  },
  button: {
    width: '80%',
    marginLeft: '10%',
    padding: 10,
    backgroundColor: '#495E57',
    borderColor: '#EDEFEE',
    borderRadius: 12
  },
  buttonText: {
    color: 'white',
    textAlign: 'center',
    fontSize: 18,
  },
  inputBox: {
    width: '80%',
    marginLeft: '10%',
    height: 40,
    margin: 12,
    borderWidth: 1,
    padding: 10,
    fontSize: 16,
    borderColor: 'black',
    borderRadius: 12,
    backgroundColor: '#EDEFEE'
  }
})