import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
} from 'react-native';

import {
  CharacterCreationScreen,
  HomeScreen,
} from './components/screens';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      page: 'home-screen',
    };
  }

  goToPage(page) {
    this.setState({page: page});
  }

  render() {
    switch (this.state.page) {
      case 'character-creation':
        return (
          <CharacterCreationScreen onGoTo={this.goToPage.bind(this)} />
        );

      case 'home-screen':
        return (
          <HomeScreen onGoTo={this.goToPage.bind(this)} />
        );

      default:
        throw "Unknown page: " + this.state.page;
    }
  }
}

AppRegistry.registerComponent('FabledLands4Android', () => App);
