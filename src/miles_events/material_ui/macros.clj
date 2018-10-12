(ns miles-events.material-ui.macros
  (:require [camel-snake-kebab.core :refer [->kebab-case]]))

(def tags
  '[MuiThemeProvider
    AppBar
    Avatar
    Backdrop
    Badge
    BottomNavigation
    BottomNavigationAction
    Button
    ButtonBase
    Card
    CardActionArea
    CardActions
    CardContent
    CardHeader
    CardMedia
    Checkbox
    Chip
    CircularProgress
    ClickAwayListener
    Collapse
    CssBaseline
    Dialog
    DialogActions
    DialogContent
    DialogContentText
    DialogTitle
    Divider
    Drawer
    ExpansionPanel
    ExpansionPanelActions
    ExpansionPanelDetails
    ExpansionPanelSummary
    Fade
    FilledInput
    FormControl
    FormControlLabel
    FormGroup
    FormHelperText
    FormLabel
    Grid
    GridList
    GridListTile
    GridListTileBar
    Grow
    Hidden
    Icon
    IconButton
    Input
    InputAdornment
    InputBase
    InputLabel
    LinearProgress
    List
    ListItem
    ListItemAvatar
    ListItemIcon
    ListItemSecondaryAction
    ListItemText
    ListSubheader
    Menu
    MenuItem
    MenuList
    MobileStepper
    Modal
    ModalManager
    NativeSelect
    NoSsr
    OutlinedInput
    Paper
    Popover
    Popper
    Portal
    Radio
    RadioGroup
    RootRef
    Select
    Slide
    Snackbar
    SnackbarContent
    Step
    StepButton
    StepConnector
    StepContent
    StepIcon
    StepLabel
    Stepper
    SvgIcon
    SwipeableDrawer
    Switch
    Tab
    Table
    TableBody
    TableCell
    TableFooter
    TableHead
    TablePagination
    TableRow
    TableSortLabel
    Tabs
    TextField
    Toolbar
    Tooltip
    Typography
    Zoom])

(defn generate-mui-fn [tname]
  `(def ~(symbol (->kebab-case (str tname)))
     (r/adapt-react-class (~'aget js/MaterialUI ~(name tname)))))

(defmacro generate-mui-fns []
  `(do ~@(map generate-mui-fn tags)))