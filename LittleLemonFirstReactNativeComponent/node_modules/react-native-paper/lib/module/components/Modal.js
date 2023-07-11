function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

import * as React from 'react';
import { Animated, BackHandler, Easing, StyleSheet, TouchableWithoutFeedback, View } from 'react-native';
import { getStatusBarHeight, getBottomSpace } from 'react-native-iphone-x-helper';
import Surface from './Surface';
import { withTheme } from '../core/theming';
const DEFAULT_DURATION = 220;
const TOP_INSET = getStatusBarHeight(true);
const BOTTOM_INSET = getBottomSpace();
/**
 * The Modal component is a simple way to present content above an enclosing view.
 * To render the `Modal` above other components, you'll need to wrap it with the [`Portal`](portal.html) component.
 *
 * <div class="screenshots">
 *   <figure>
 *     <img class="medium" src="screenshots/modal.gif" />
 *   </figure>
 * </div>
 *
 * ## Usage
 * ```js
 * import * as React from 'react';
 * import { Modal, Portal, Text, Button, Provider } from 'react-native-paper';
 *
 * const MyComponent = () => {
 *   const [visible, setVisible] = React.useState(false);
 *
 *   const showModal = () => setVisible(true);
 *   const hideModal = () => setVisible(false);
 *   const containerStyle = {backgroundColor: 'white', padding: 20};
 *
 *   return (
 *     <Provider>
 *       <Portal>
 *         <Modal visible={visible} onDismiss={hideModal} contentContainerStyle={containerStyle}>
 *           <Text>Example Modal.  Click outside this area to dismiss.</Text>
 *         </Modal>
 *       </Portal>
 *       <Button style={{marginTop: 30}} onPress={showModal}>
 *         Show
 *       </Button>
 *     </Provider>
 *   );
 * };
 *
 * export default MyComponent;
 * ```
 */

class Modal extends React.Component {
  constructor(...args) {
    super(...args);

    _defineProperty(this, "state", {
      opacity: new Animated.Value(this.props.visible ? 1 : 0),
      rendered: this.props.visible
    });

    _defineProperty(this, "handleBack", () => {
      if (this.props.dismissable) {
        this.hideModal();
      }

      return true;
    });

    _defineProperty(this, "showModal", () => {
      BackHandler.removeEventListener('hardwareBackPress', this.handleBack);
      BackHandler.addEventListener('hardwareBackPress', this.handleBack);
      const {
        opacity
      } = this.state;
      const {
        scale
      } = this.props.theme.animation;
      Animated.timing(opacity, {
        toValue: 1,
        duration: scale * DEFAULT_DURATION,
        easing: Easing.out(Easing.cubic),
        useNativeDriver: true
      }).start();
    });

    _defineProperty(this, "hideModal", () => {
      BackHandler.removeEventListener('hardwareBackPress', this.handleBack);
      const {
        opacity
      } = this.state;
      const {
        scale
      } = this.props.theme.animation;
      Animated.timing(opacity, {
        toValue: 0,
        duration: scale * DEFAULT_DURATION,
        easing: Easing.out(Easing.cubic),
        useNativeDriver: true
      }).start(({
        finished
      }) => {
        if (!finished) {
          return;
        }

        if (this.props.visible && this.props.onDismiss) {
          this.props.onDismiss();
        }

        if (this.props.visible) {
          this.showModal();
        } else {
          this.setState({
            rendered: false
          });
        }
      });
    });
  }

  static getDerivedStateFromProps(nextProps, prevState) {
    if (nextProps.visible && !prevState.rendered) {
      return {
        rendered: true
      };
    }

    return null;
  }

  componentDidUpdate(prevProps) {
    if (prevProps.visible !== this.props.visible) {
      if (this.props.visible) {
        this.showModal();
      } else {
        this.hideModal();
      }
    }
  }

  componentWillUnmount() {
    BackHandler.removeEventListener('hardwareBackPress', this.handleBack);
  }

  render() {
    const {
      rendered,
      opacity
    } = this.state;
    if (!rendered) return null;
    const {
      children,
      dismissable,
      style,
      theme,
      contentContainerStyle,
      overlayAccessibilityLabel
    } = this.props;
    const {
      colors
    } = theme;
    return /*#__PURE__*/React.createElement(Animated.View, {
      pointerEvents: this.props.visible ? 'auto' : 'none',
      accessibilityViewIsModal: true,
      accessibilityLiveRegion: "polite",
      style: StyleSheet.absoluteFill,
      onAccessibilityEscape: this.hideModal
    }, /*#__PURE__*/React.createElement(TouchableWithoutFeedback, {
      accessibilityLabel: overlayAccessibilityLabel,
      accessibilityRole: "button",
      disabled: !dismissable,
      onPress: dismissable ? this.hideModal : undefined,
      importantForAccessibility: "no"
    }, /*#__PURE__*/React.createElement(Animated.View, {
      style: [styles.backdrop, {
        backgroundColor: colors.backdrop,
        opacity
      }]
    })), /*#__PURE__*/React.createElement(View, {
      style: [styles.wrapper, {
        marginTop: TOP_INSET,
        marginBottom: BOTTOM_INSET
      }, style],
      pointerEvents: "box-none"
    }, /*#__PURE__*/React.createElement(Surface, {
      style: [{
        opacity
      }, styles.content, contentContainerStyle]
    }, children)));
  }

}

_defineProperty(Modal, "defaultProps", {
  dismissable: true,
  visible: false,
  overlayAccessibilityLabel: 'Close modal'
});

export default withTheme(Modal);
const styles = StyleSheet.create({
  backdrop: {
    flex: 1
  },
  wrapper: { ...StyleSheet.absoluteFillObject,
    justifyContent: 'center'
  },
  content: {
    backgroundColor: 'transparent',
    justifyContent: 'center'
  }
});
//# sourceMappingURL=Modal.js.map