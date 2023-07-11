import { View, Text } from 'react-native';

export default function LittleLemonHeader() {
  return (
    <View style={{ flex: 0.1, backgroundColor: '#F4CE14' }}>
      <Text
        style={{
          padding: 20,
          fontSize: 30,
          color: 'black',
          textAlign: 'center',
        }}>
        Little Lemon
      </Text>
    </View>
  );
}
