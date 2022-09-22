import TopMenu from "./components/UI/TopMenu";
import React, {useState} from "react";
import {Route, Switch} from "react-router-dom";
import Home from "./components/pages/Home";
import RecipeDetails from "./components/recipe/RecipeDetails";

const App = () => {
    const [searchBy, setSearchBy] = useState("");
    const [searchByCalories, setSearchByCalories] = useState([]);
    const [searchByCarbs, setSearchByCarbs] = useState([]);
    const [searchByFat, setSearchByFat] = useState([]);
    const [searchByProtein, setSearchByProtein] = useState([]);

    const handleCallbackTopMenu = (ingredientInputRef) => {
        setSearchBy(ingredientInputRef);
    }
    const handleCallBackForSearchByCalories = (minCaloriesRef,maxCaloriesRef) => {
        setSearchByCalories([minCaloriesRef,maxCaloriesRef]);
    }
    const handleCallBackForSearchByCarbohydrate = (minCarbohydrateRef,maxCarbohydrateRef) => {
        setSearchByCarbs([minCarbohydrateRef,maxCarbohydrateRef]);
    }
    const handleCallBackForSearchByFat = (minFatRef,maxFatRef) => {
        setSearchByFat([minFatRef,maxFatRef]);
    }
    const handleCallBackForSearchByProtein = (minProteinRef,maxProteinRef) => {
        setSearchByProtein([minProteinRef,maxProteinRef]);
    }


    return (
        <main>
            <TopMenu onParentCallback={handleCallbackTopMenu}
                     onParentCallBackForSearchByCalories={handleCallBackForSearchByCalories}
                     onParentCallBackForSearchByCarbohydrate={handleCallBackForSearchByCarbohydrate}
                     onParentCallBackForSearchByFat={handleCallBackForSearchByFat}
                     onParentCallBackForSearchByProtein={handleCallBackForSearchByProtein}
            />
            <Switch>
                <Route path="/" exact={true}>
                    <Home searchBy={searchBy}
                          searchByCalories={searchByCalories}
                          searchByCarbs={searchByCarbs}
                          searchByFat={searchByFat}
                          searchByProtein={searchByProtein}/>
                </Route>
                <Route path='/recipe-details/:recipeName/:recipeText' exact={true}>
                    <RecipeDetails/>
                </Route>
            </Switch>
        </main>
    );
}

export default App;
