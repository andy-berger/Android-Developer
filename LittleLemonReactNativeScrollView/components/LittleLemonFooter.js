import * as React from 'react';
import { View, Text } from 'react-native';

export default function LittleLemonFooter() {
  return (
    <View
      style={{
        backgroundColor: '#F4CE14',
        marginBottom: 10,
      }}>
      <Text
        style={{
          fontSize: 18,
          color: 'black',
          textAlign: 'center',
        }}>
        All rights reserved by Little Lemon, 2022{' '}
      </Text>
    </View>
  );
}
