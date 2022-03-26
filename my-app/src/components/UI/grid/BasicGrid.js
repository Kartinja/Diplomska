import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';

const BasicGrid = (props) => {
    return (
        <Box sx={{flexGrow: 1}}>
            <Grid container spacing={1}>
                <Grid item xs={4}>
                    <img src={props.image} alt="food"/>
                </Grid>
                <Grid item xs={8}>
                    <p style={{textAlign:"justify"}}>{props.text}</p>
                </Grid>
            </Grid>
        </Box>
    );
}
export default BasicGrid;
