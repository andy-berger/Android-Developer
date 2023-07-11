"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var React = _interopRequireWildcard(require("react"));

var _reactNative = require("react-native");

var _reactNativeIphoneXHelper = require("react-native-iphone-x-helper");

var _Surface = _interopRequireDefault(require("./Surface"));

var _theming = require("../core/theming");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function () { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || typeof obj !== "object" && typeof obj !== "function") { return { default: obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj.default = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

const DEFAULT_DURATION = 220;
const TOP_INSET = (0, _reactNativeIphoneXHelper.getStatusBarHeight)(true);
const BOTTOM_INSET = (0, _reactNativeIphoneXHelper.getBottomSpace)();
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
      opacity: new _reactNative.Animated.Value(this.props.visible ? 1 : 0),
      rendered: this.props.visible
    });

    _defineProperty(this, "handleBack", () => {
      if (this.props.dismissable) {
        this.hideModal();
      }

      return true;
    });

    _defineProperty(this, "showModal", () => {
      _reactNative.BackHandler.removeEventListener('hardwareBackPress', this.handleBack);

      _reactNative.BackHandler.addEventListener('hardwareBackPress', this.handleBack);

      const {
        opacity
      } = this.state;
      const {
        scale
      } = this.props.theme.animation;

      _reactNative.Animated.timing(opacity, {
        toValue: 1,
        duration: scale * DEFAULT_DURATION,
        easing: _reactNative.Easing.out(_reactNative.Easing.cubic),
        useNativeDriver: true
      }).start();
    });

    _defineProperty(this, "hideModal", () => {
      _reactNative.BackHandler.removeEventListener('hardwareBackPress', this.handleBack);

      const {
        opacity
      } = this.state;
      const {
        scale
      } = this.props.theme.animation;

      _reactNative.Animated.timing(opacity, {
        toValue: 0,
        duration: scale * DEFAULT_DURATION,
        easing: _reactNative.Easing.out(_reactNative.Easing.cubic),
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
    _reactNative.BackHandler.removeEventListener('hardwareBackPress', this.handleBack);
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
    return /*#__PURE__*/React.createElement(_reactNative.Animated.View, {
      pointerEvents: this.props.visible ? 'auto' : 'none',
      accessibilityViewIsModal: true,
      accessibilityLiveRegion: "polite",
      style: _reactNative.StyleSheet.absoluteFill,
      onAccessibilityEscape: this.hideModal
    }, /*#__PURE__*/React.createElement(_reactNative.TouchableWithoutFeedback, {
      accessibilityLabel: overlayAccessibilityLabel,
      accessibilityRole: "button",
      disabled: !dismissable,
      onPress: dismissable ? this.hideModal : undefined,
      importantForAccessibility: "no"
    }, /*#__PURE__*/React.createElement(_reactNative.Animated.View, {
      style: [styles.backdrop, {
        backgroundColor: colors.backdrop,
        opacity
      }]
    })), /*#__PURE__*/React.createElement(_reactNative.View, {
      style: [styles.wrapper, {
        marginTop: TOP_INSET,
        marginBottom: BOTTOM_INSET
      }, style],
      pointerEvents: "box-none"
    }, /*#__PURE__*/React.createElement(_Surface.default, {
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

var _default = (0, _theming.withTheme)(Modal);

exports.default = _default;

const styles = _reactNative.StyleSheet.create({
  backdrop: {
    flex: 1
  },
  wrapper: { ..._reactNative.StyleSheet.absoluteFillObject,
    justifyContent: 'center'
  },
  content: {
    backgroundColor: 'transparent',
    justifyContent: 'center'
  }
});
//# sourceMappingURL=Modal.js.map