import { View, Text, StyleSheet, Image, Pressable } from 'react-native';

const WelcomeScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <View style={styles.headerWrapper}>
        <Image
          style={styles.logo}
          source={require("../assets/little-lemon-logo.png")}
          resizeMode="cover"
          accessible={true}
          accessibilityLabel={"Little Lemon Logo"}
        />
      </View>
      <Text style={styles.regularText}>
          Little Lemon, your local Mediterranean Bistro
      </Text>
      <Pressable style={styles.button} onPress={() => navigation.navigate("Subscribe")}>
        <Text style={styles.buttonText}>Newsletter</Text>
      </Pressable>
    </View>
  );
};

export default WelcomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  headerWrapper: {
    flexDirection: "row",
    justifyContent: "center",
    marginTop: 150
  },
  regularText: {
    fontSize: 22,
    padding: 20,
    marginVertical: 20,
    color: 'dark-gray',
    textAlign: 'center',
  },
  logo: {
    width: 85,
    height: 155
  },
  button: {
    position: 'absolute',
    bottom: 0,
    width: '80%',
    padding: 10,
    margin: '10%',
    backgroundColor: '#495E57',
    borderColor: '#EDEFEE',
    borderWidth: 2,
    borderRadius: 12
  },
  buttonText: {
    color: 'white',
    textAlign: 'center',
    fontSize: 18,
  },
})