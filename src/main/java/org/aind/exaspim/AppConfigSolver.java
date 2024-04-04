package org.aind.exaspim;

import net.preibisch.bigstitcher.spark.Solver;

public class AppConfigSolver extends Solver {

        protected Double solver_lambda = 0.1; // The python json model cannot use lambda as a field name
        public void setXmlPath( String xmlPath )
        {
            this.xmlPath = xmlPath;
        }

        public void printSettings() {
            System.out.println(globalOptType);
        }
}
