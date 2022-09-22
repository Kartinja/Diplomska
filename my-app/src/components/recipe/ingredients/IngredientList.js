import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import Ingredient from "./Ingredient";
const IngredientList = (props) => {
    const handleCallback = (isChanged) => {
        childToParent(isChanged);
    }
    const childToParent = (isChanged) => {
        props.onParentCallback(isChanged);
    }
    return (
        <div>
            {!props.ingredientQuantity &&
            <p className="w3-center" style={{color: "red"}}>You must enter name and ingredients in the recipe!</p>}
            {props.ingredientQuantity && <TableContainer component={Paper}>
                <Table sx={{minWidth: 650}} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Ingredient</TableCell>
                            <TableCell>Quantity(g)</TableCell>
                            <TableCell align="right">Calories</TableCell>
                            <TableCell align="right">Fat(g)</TableCell>
                            <TableCell align="right">Carbs(g)</TableCell>
                            <TableCell align="right">Protein(g)</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {props.ingredients.map((row) => (
                            props.ingredientQuantity.map((ingredient) => (ingredient.name === row.name &&
                                <Ingredient row={row} quantity={ingredient.quantity} recipeName={props.recipeName}
                                            recipeText={props.recipeText} parentCallBack={handleCallback}/>))
                        ))}
                        <TableRow>

                        <TableCell align="right"/>
                        <TableCell align="right"/>
                        <TableCell align="right">{props.energy}</TableCell>
                        <TableCell align="right">{props.fat}</TableCell>
                        <TableCell align="right">{props.carbs}</TableCell>
                        <TableCell align="right">{props.protein}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>}
        </div>
    );
}
export default IngredientList;