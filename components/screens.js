import React, {
  Component,
  ListView,
  Text,
  View,
} from 'react-native';

import {
  styles
} from './styles';

import {
  getCharacterCreationInfo
} from './books';

export class CharacterCreationScreen extends Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      adventurers: [],
    };

    getCharacterCreationInfo(1)
      .then(info => {
        this.setState({
          info: info
        });
      });
  }

  goToHomeScreen() {
    this.props.onGoTo('home-screen');
  }

  createDataSource() {
    var ds = new ListView.DataSource({
      rowHasChanged: (r1, r2) => r1 !== r2
    });
    return ds.cloneWithRows(this.state.adventurers);
  }

  renderCharacter(character) {
    return (
      <Text>{character.name}</Text>
    )
  }

  render() {
    return (
      <View>
        <Text style={styles.header}>
          Create a character
        </Text>
        <Text>Initial gold: {this.initialGold}</Text>
        <Text onPress={this.goToHomeScreen.bind(this)}>
          Back
        </Text>
      </View>
    );
  }
}


export class HomeScreen extends Component {
  goToCreateCharacterScreen() {
    this.props.onGoTo('character-creation');
  }

  render() {
    return (
      <View>
        <Text style={styles.header}>
          Fabled Lands
        </Text>
        <Text onPress={this.goToCreateCharacterScreen.bind(this)}>
          Start new character
        </Text>
        <Text>
          Continue an existing character
        </Text>
      </View>
    );
  }
}

