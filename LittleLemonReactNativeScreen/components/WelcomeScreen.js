import { View, Text } from 'react-native';

export default function WelcomeScreen() {
  return (
    <View style={{ flex: 1 }}>
      <Text
        style={{
          padding: 40,
          fontSize: 30,
          color: '#EDEFEE',
          textAlign: 'center',
        }}>
        Welcome to Little Lemon
      </Text>
      <Text
        style={{
          padding: 20,
          marginVertical: 8,
          fontSize: 24,
          color: '#EDEFEE',
          textAlign: 'center',
        }}>
        Little Lemon is a charming neighborhood bistro that serves simple food and classic cocktails in a lively but casual environment. We would love to hear more about your experience with us!
      </Text>
    </View>
  );
}
