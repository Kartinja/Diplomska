import TopMenu from "./components/UI/TopMenu";
import React, {useState} from "react";
import {Route, Switch} from "react-router-dom";
import Home from "./components/pages/Home";
import RecipeDetails from "./components/recipe/RecipeDetails";

const App = () => {
    const [searchBy, setSearchBy] = useState("");
    const handleCallbackTopMenu = (ingredientInputRef) => {
        setSearchBy(ingredientInputRef);
        console.log(this.document.location);
    }

    return (
        <main>
            <TopMenu onParentCallback={handleCallbackTopMenu}/>
            <Switch>
                <Route path="/" exact={true}>
                    <Home searchBy={searchBy}/>
                </Route>
                <Route path='/recipe-details/:recipeName/:recipeText' exact={true}>
                    <RecipeDetails/>
                </Route>
            </Switch>
        </main>
    );
}

export default App;
