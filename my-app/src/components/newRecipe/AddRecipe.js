import React, {useRef} from 'react';
import classes from './AddRecipe.module.css';

function AddRecipe(props) {
    const nameRef = useRef('');
    const recipeTextRef = useRef('');

    function submitHandler(event) {
        event.preventDefault();
        // could add validation here...
        const recipe = {
            name: nameRef.current.value,
            text: recipeTextRef.current.value
        };

        props.onAddRecipe(recipe);
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
                <button onClick={addRecipeClose} id="addRecipeBtn" style={{"padding":"8px","width":"10%"}}>Add Recipe</button>
                <button id="addAnotherRecipeBtn" style={{"padding":"8px"}}>Add Another Recipe</button>
            </div>
        </form>
    );
}

export default AddRecipe;
