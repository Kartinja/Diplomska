import React, {useRef, useState} from 'react';
import classes from './AddRecipe.module.css';
import FileUploader from "../../fileHandler/FileUploader";
import {Alert, Button} from "@mui/material";

function AddRecipe(props) {
    const nameRef = useRef('');
    const recipeTextRef = useRef('');
    const [image, setImage] = useState('');
    const [isRecipeValid, setIsRecipeValid] = useState(true);
    const formData = new FormData();

    async function addRecipeHandler() {
        const response = await fetch('http://localhost:8080/recipe', {
            method: 'POST',
            body: formData,
            headers: {
                'Accept' :'application/json'
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
    const handleFile = (file) => {
        setImage(file);
    }

    function submitHandler(event) {
        event.preventDefault();
        // could add validation here...
        formData.append('recipeName',nameRef.current.value);
        formData.append('recipeText',recipeTextRef.current.value);
        formData.append('image',image);

        addRecipeHandler();
        recipeTextRef.current.value = "";
        nameRef.current.value = "";
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
                    <label htmlFor='picture'>Recipe picture</label>
                    <FileUploader onFileSelect={handleFile}/>
                </div>
                <div className={classes.control}>
                    <label htmlFor='opening-text'>Recipe Text</label>
                    <textarea style={{"resize": "none"}} rows='10' id='opening-text' ref={recipeTextRef}/>
                </div>
                <Button type="submit" variant="contained" onClick={addRecipeClose} id="addRecipeBtn" style={{"padding": "8px", "width": "10%",margin:"5px"}}>Add
                    Recipe</Button>
                <Button type="submit"  variant="contained" id="addAnotherRecipeBtn" style={{"padding": "8px"}}>Add Another Recipe</Button>
                <Button variant="outlined" onClick={addRecipeClose} style={{"padding": "8px", "width": "10%",marginLeft:"750px"}}>Close</Button>
            </div>
            {!isRecipeValid &&
            <Alert severity="error">You must enter name and ingredients in the recipe!</Alert>}

        </form>
    );
}

export default AddRecipe;
