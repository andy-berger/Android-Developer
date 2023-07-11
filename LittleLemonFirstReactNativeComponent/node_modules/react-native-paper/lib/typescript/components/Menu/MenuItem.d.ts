import * as React from 'react';
import { StyleProp, TextStyle, ViewStyle } from 'react-native';
import { IconSource } from '../Icon';
declare type Props = {
    /**
     * Title text for the `MenuItem`.
     */
    title: React.ReactNode;
    /**
     * Icon to display for the `MenuItem`.
     */
    icon?: IconSource;
    /**
     * Whether the 'item' is disabled. A disabled 'item' is greyed out and `onPress` is not called on touch.
     */
    disabled?: boolean;
    /**
     * Function to execute on press.
     */
    onPress?: () => void;
    /**
     * @optional
     */
    style?: StyleProp<ViewStyle>;
    contentStyle?: StyleProp<ViewStyle>;
    titleStyle?: StyleProp<TextStyle>;
    /**
     * TestID used for testing purposes
     */
    testID?: string;
    /**
     * Accessibility label for the Touchable. This is read by the screen reader when the user taps the component.
     */
    accessibilityLabel?: string;
};
/**
 * A component to show a single list item inside a Menu.
 *
 * <div class="screenshots">
 *   <figure>
 *     <img class="medium" src="screenshots/menu-item.png" />
 *   </figure>
 * </div>
 *
 * ## Usage
 * ```js
 * import * as React from 'react';
 * import { View } from 'react-native';
 * import { Menu } from 'react-native-paper';
 *
 * const MyComponent = () => (
 *   <View style={{ flex: 1 }}>
 *     <Menu.Item icon="redo" onPress={() => {}} title="Redo" />
 *     <Menu.Item icon="undo" onPress={() => {}} title="Undo" />
 *     <Menu.Item icon="content-cut" onPress={() => {}} title="Cut" disabled />
 *     <Menu.Item icon="content-copy" onPress={() => {}} title="Copy" disabled />
 *     <Menu.Item icon="content-paste" onPress={() => {}} title="Paste" />
 *   </View>
 * );
 *
 * export default MyComponent;
 * ```
 */
declare function MenuItem({ icon, title, disabled, onPress, style, contentStyle, testID, titleStyle, accessibilityLabel, }: Props): JSX.Element;
declare namespace MenuItem {
    var displayName: string;
}
export default MenuItem;
export { MenuItem };
