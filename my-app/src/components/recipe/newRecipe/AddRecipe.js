import React, {useRef, useState} from 'react';
import classes from './AddRecipe.module.css';

function AddRecipe(props) {
    const nameRef = useRef('');
    const recipeTextRef = useRef('');
    const [isRecipeValid, setIsRecipeValid] = useState(true);

    async function addRecipeHandler(recipe) {
        if(recipe.name.trim()===''){
            setIsRecipeValid(false);
            return;
        }
        const response = await fetch('http://localhost:8080/recipe', {
            method: 'POST',
            body: JSON.stringify(recipe),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            setIsRecipeValid(false);
            return;
        }
        childToParent();
        setIsRecipeValid(true);
    }
    const childToParent = () => {
        props.onParentCallback(true);
    }

    function submitHandler(event) {
        event.preventDefault();
        // could add validation here...
        const recipe = {
            name: nameRef.current.value,
            text: recipeTextRef.current.value.trim()
        };
        addRecipeHandler(recipe);
        recipeTextRef.current.value="";
        nameRef.current.value="";
    }
    function addRecipeClose() {
        document.getElementById("addRecipe").style.display = "none";
    }

    return (
        <form onSubmit={submitHandler}>
            <div id="addRecipe" style={{"display": "none"}}>
                <div className={classes.control}>
                    <label htmlFor='title'>Recipe Name</label>
                    <input type='text' id='title' ref={nameRef}/>
                </div>
                <div className={classes.control}>
                    <label htmlFor='opening-text'>Recipe Text</label>
                    <textarea style={{"resize": "none"}} rows='10' id='opening-text' ref={recipeTextRef}/>
                </div>
                <button onClick={addRecipeClose} id="addRecipeBtn" style={{"padding": "8px", "width": "10%"}}>Add
                    Recipe
                </button>
                <button id="addAnotherRecipeBtn" style={{"padding": "8px"}}>Add Another Recipe</button>
            </div>
            {!isRecipeValid && <p className="w3-center" style={{color:"red"}}>You must enter name and ingredients in the recipe!</p>}
        </form>
    );
}

export default AddRecipe;
