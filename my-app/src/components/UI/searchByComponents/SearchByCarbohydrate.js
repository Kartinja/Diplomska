import React, {useRef} from "react";
import {Link} from "react-router-dom";
import {useHistory} from "react-router-dom";
import search from "../../images/search.png";
import {TextField} from "@mui/material";

const SearchByCarbohydrate = (props) => {
    const minCarbsRef = useRef('');
    const maxCarbsRef = useRef('');
    let history = useHistory();

    const formSubmissionHandler = event => {
        event.preventDefault();
        history.push("/");
        props.onParentCallBackForSearchByCarbohydrate(minCarbsRef.current.value, maxCarbsRef.current.value)
    }

    return (
        <form onSubmit={formSubmissionHandler}>
            <Link style={{textDecoration: "none", cursor: "default",marginRight:131}}>Search by Carbohydrate</Link>
            <input style={{width: "150px", marginLeft: "30px", marginRight: "5px"}} ref={minCarbsRef}
                   defaultValue={0}/>
            <input style={{width: "150px"}} ref={maxCarbsRef}/>
            {/*<TextField id="standard-basic" label="Minimum Carbs" variant="standard" style={{width:"150px"}} ref={minCarbsRef}/>*/}
            {/*<TextField id="standard-basic" label="Maximum Carbs" variant="standard" style={{width:"150px"}} ref={maxCarbsRef} />*/}
            <button type="submit" className="search-button">
                <img src={search} alt="Magnifying glass"/>
            </button>
        </form>
    )


}

export default SearchByCarbohydrate;