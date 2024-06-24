# Scaffold

https://developer.android.com/develop/ui/compose/components/scaffold

- basic material design의 layout 구조를 구현.

basic material design의 layout은 무엇?
https://m3.material.io/foundations/layout/understanding-layout/overview

- fundamental structure complex user interfaces.

- app bars and floating action buttons

# 파라미터 설명

modifier - the Modifier to be applied to this scaffold
topBar - top app bar of the screen, typically a SmallTopAppBar
bottomBar - bottom bar of the screen, typically a NavigationBar
snackbarHost - component to host Snackbars that are pushed to be shown via
SnackbarHostState.showSnackbar, typically a SnackbarHost
floatingActionButton - Main action button of the screen, typically a FloatingActionButton
floatingActionButtonPosition - position of the FAB on the screen. See FabPosition.
containerColor - the color used for the background of this scaffold. Use Color.Transparent to have
no color.
contentColor - the preferred color for content inside this scaffold. Defaults to either the matching
content color for containerColor, or to the current LocalContentColor if containerColor is not a
color from the theme.
contentWindowInsets - window insets to be passed to content slot via PaddingValues params. Scaffold
will take the insets into account from the top/bottom only if the topBar/ bottomBar are not present,
as the scaffold expect topBar/bottomBar to handle insets instead. Any insets consumed by other
insets padding modifiers or consumeWindowInsets on a parent layout will be excluded from
contentWindowInsets.
content - content of the screen. The lambda receives a PaddingValues that should be applied to the
content root via Modifier.padding and Modifier.consumeWindowInsets to properly offset top and bottom
bars. If using Modifier.verticalScroll, apply this modifier to the child of the scroll, and not on
the scroll itself.

```
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
)
```

# Scaffold 프로젝트를 구현하면서 기억나는 특징
- Theme 컬러를 잘 활용하면 좋은 것 같다.
- Scaffold contents 부분에 패딩을 주고 싶다면 contentWindowInsets 을 사용해보기
- contentWindowInsets top bottom 사용 시 appbar가 있다면 적용 안됨.
- actionButton을 추가하면 SnackBar가 ActionBar 위로 위치가 이동 함.
- 