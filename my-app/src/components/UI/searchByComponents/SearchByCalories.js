import React, {useRef} from "react";
import {Link} from "react-router-dom";
import {useHistory} from "react-router-dom";
import search from "../../images/search.png";
import {TextField} from "@mui/material";

const SearchByCalories = (props) => {
    const minCaloriesRef = useRef('');
    const maxCaloriesRef = useRef('');
    let history = useHistory();

    const formSubmissionHandler = event => {
        event.preventDefault();
        history.push("/");
        props.onParentCallBackForSearchByCalories(minCaloriesRef.current.value, maxCaloriesRef.current.value)
    }

    return (
        <form onSubmit={formSubmissionHandler}>
            <Link style={{textDecoration: "none", cursor: "default",marginRight:188}}>Search by Calories</Link>
            <input style={{width: "150px", marginLeft: "30px", marginRight: "5px"}} ref={minCaloriesRef}
                   defaultValue={0}/>
            <input style={{width: "150px"}} ref={maxCaloriesRef}/>
            {/*<TextField id="standard-basic" label="Minimum Calories" variant="standard" style={{width:"150px"}} ref={minCaloriesRef} />
            <TextField id="standard-basic" label="Maximum Calories" variant="standard" style={{width:"150px"}} ref={maxCaloriesRef}/>*/}
            <button type="submit" className="search-button">
                <img src={search} alt="Magnifying glass"/>
            </button>
        </form>
    )


}

export default SearchByCalories;